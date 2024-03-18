package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LifterSubsystem;

/**
 * A command to move the robot's hook with variable power (passed in as
 * {@link DoubleSupplier}). Written
 * explicitly for pedagogical purposes - actual code should inline a command
 * this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class RaiseRobot extends Command {
    private final LifterSubsystem m_lifter;
    private final DoubleSupplier m_power;

    /* Raise the robot by pulling the hook down */
    public RaiseRobot(LifterSubsystem subsystem, DoubleSupplier raisePower) {
        m_lifter = subsystem;
        m_power = raisePower;
        addRequirements(m_lifter);
    }

    /* execute the command to raise the robot */
    @Override
    public void execute() {
        m_lifter.moveHook(-m_power.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_lifter.stopHook();
    }
}
