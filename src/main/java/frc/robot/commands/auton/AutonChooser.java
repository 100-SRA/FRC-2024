package frc.robot.commands.auton;

import java.util.EnumMap;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class AutonChooser {
    public enum AutonOption {
        DO_NOTHING("NOTHING"),
        LEAVE_STARTING_ZONE("LEAVE START ZONE"),
        THROW_NOTE("1NOTES - SPKR CNTR"),
        SPEAKER_CENTER_NOTES("2NOTES - SPKR CNTR");

        public final String m_description;

        AutonOption(String description) {
            m_description = description;
        }

        @Override
        public String toString() {
            return name() + ": " + m_description;
        }
    }

    private AutonChooser() {
        // utility class, do not create instances!
    }

    private static EnumMap<AutonOption, Command> autonChooserMap = new EnumMap<>(AutonOption.class);
    private static final SendableChooser<AutonOption> autonNTChooser = new SendableChooser<AutonOption>();

    static {
        SmartDashboard.putData("AutonChooser", autonNTChooser);
    }

    public static void assignAutonCommand(AutonOption auton, Command command) {
        autonChooserMap.put(auton, command);

        autonNTChooser.addOption(auton.m_description, auton);
    }

    public static void setDefaultAuton(AutonOption auton) {
        autonNTChooser.setDefaultOption(auton.m_description, auton);
    }

    public static Command getAuton(AutonOption auton) {
        return autonChooserMap.computeIfAbsent(auton, a -> Commands.print("warning: empty auton!")
            .withName("InvalidAuton"));
    }

    public static Command getChosenAutonCmd() {
        return getAuton(autonNTChooser.getSelected());
    }
}
