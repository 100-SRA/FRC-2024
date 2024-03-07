package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.units.*;
import frc.robot.subsystems.ArmSubsystem;

/* This command sets the Arm position to a specific angle */
public class SetArmPosition extends Command {
    private final ArmSubsystem m_arm;
    private final Measure<Angle> m_position;

    public SetArmPosition(ArmSubsystem arm, Measure<Angle> position) {
        m_arm = arm;
        m_position = position;
        addRequirements(m_arm);
    }

    @Override
    public void initialize() {
        m_arm.setGoal(m_position.magnitude());
        m_arm.enable();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
