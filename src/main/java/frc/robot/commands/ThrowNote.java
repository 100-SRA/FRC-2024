package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NoteThrowerSubsystem;

/*
 * Command to start the thrower wheels spinning to launch a note.
 */
public class ThrowNote extends Command {
    private final NoteThrowerSubsystem m_armSubsystem;

    public ThrowNote(NoteThrowerSubsystem subsystem) {
        m_armSubsystem = subsystem;
        addRequirements(m_armSubsystem);
    }

    @Override
    public void initialize() {
        m_armSubsystem.spinThrowers();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_armSubsystem.stopThrowers();
    }
}
