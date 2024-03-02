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

    public DefaultArmLift(ArmSubsystem subsystem, DoubleSupplier lift) {
        m_arm = subsystem;
        m_lift = lift;
        addRequirements(m_arm);
    }

    /* execute the command */
    @Override
    public void execute() {
        // input joystick value into arm motor control
        m_arm.liftArm(m_lift.getAsDouble());
    }
}
