package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

/*
 * Command to start the thrower wheels spinning to launch a note.
 */
public class SpinThrowers extends Command {
    private final ArmSubsystem m_armSubsystem;

    public SpinThrowers(ArmSubsystem subsystem) {
        m_armSubsystem = subsystem;
        addRequirements(m_armSubsystem);
    }

    @Override
    public void initialize() {
        m_armSubsystem.spinThrowers();
    }

    @Override
    public void end(boolean interrupted) {
        m_armSubsystem.stopThrowers();
    }
}
