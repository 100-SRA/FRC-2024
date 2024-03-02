package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NoteIntakeSubsystem;

public class SwitchIntakeDirection extends Command {
    private final NoteIntakeSubsystem m_intakeSystem;

    public SwitchIntakeDirection(NoteIntakeSubsystem subsystem) {
        m_intakeSystem = subsystem;
        addRequirements(m_intakeSystem);
    }

    @Override
    public void initialize() {
        m_intakeSystem.toggleDirection();
    }

    @Override
    public boolean isFinished() {
        /* command only does one thing and then exits */
        return true;
    }
}
