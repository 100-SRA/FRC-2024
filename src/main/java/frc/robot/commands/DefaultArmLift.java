package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

/**
 * A command to move the robot's arm with joystick input (passed in as
 * {@link DoubleSupplier}). Written
 * explicitly for pedagogical purposes - actual code should inline a command
 * this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DefaultArmLift extends Command {
    private final ArmSubsystem m_arm;
    private final DoubleSupplier m_lift;
    private final DoubleSupplier m_speedMultiplier;

    public DefaultArmLift(ArmSubsystem subsystem, DoubleSupplier lift, DoubleSupplier speedMultiplier) {
        m_arm = subsystem;
        m_lift = lift;
        m_speedMultiplier = speedMultiplier;
        addRequirements(m_arm);
    }

    /* execute the command */
    @Override
    public void execute() {
        // set speed multiplier factor based on joystick dial
        // need to convert from joystick dial's range of 1.0 at bottom, -1.0 at top to
        // desired range of 0.0 (0% speed) at bottom to 1.0 (100% speed) at top
        // steps to do so:
        // 1. invert (*= -1) -> -1.0 at bottom, +1.0 at top
        // 2. add 1 -> 0.0 at bottom, +2.0 at top
        // 3. divide by 2 -> 0.0 at bottom, +1.0 at top
        double joystickDialValue;
        joystickDialValue = m_speedMultiplier.getAsDouble();
        joystickDialValue = joystickDialValue * -1;
        joystickDialValue = joystickDialValue + 1;
        joystickDialValue = joystickDialValue / 2;
        m_arm.setSpeedMultiplier(joystickDialValue);

        // now input joystick value into arm motor control
        m_arm.liftArm(m_lift.getAsDouble());
    }
}
