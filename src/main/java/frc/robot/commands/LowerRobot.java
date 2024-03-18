package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LifterSubsystem;

/**
 * A command to move the robot's hook with joystick input (passed in as
 * {@link DoubleSupplier}). Written
 * explicitly for pedagogical purposes - actual code should inline a command
 * this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class LowerRobot extends Command {
    private final LifterSubsystem m_lifter;
    private final DoubleSupplier m_power;

    /* Lower the robot by pulling the hook down */
    public LowerRobot(LifterSubsystem subsystem, DoubleSupplier lowerPower) {
        m_lifter = subsystem;
        m_power = lowerPower;
        addRequirements(m_lifter);
    }

    /* execute the command to lower the robot */
    @Override
    public void execute() {
        m_lifter.moveHook(m_power.getAsDouble());
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
