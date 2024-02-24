package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class ChangeDriveSpeed extends Command {
    private final DriveSubsystem m_drive;
    private final DoubleSupplier m_speedMultiplier;

    public ChangeDriveSpeed(DriveSubsystem subsystem, DoubleSupplier speedMultiplier) {
        m_drive = subsystem;
        m_speedMultiplier = speedMultiplier;
        addRequirements(m_drive);

    }

    @Override
    public void execute() {
        double JoystickDialValue;
        JoystickDialValue = m_speedMultiplier.getAsDouble(); // the code is getting the value from the joystick dial:
                                                             // +1.0 at bottom, -1.0 at top
        JoystickDialValue = JoystickDialValue * -1; // the code has switched it to +1.0 at the top and -1.0 at the
                                                    // bottom
        JoystickDialValue = JoystickDialValue + 1;// the code has switched it to +2.0 at the top and 0.0 at the bottom
        JoystickDialValue = JoystickDialValue / 2;// the code has switched it to +1.0 at the top and 0.0 at the bottom
        m_drive.setSpeedmultiplier(JoystickDialValue);
    }
}
