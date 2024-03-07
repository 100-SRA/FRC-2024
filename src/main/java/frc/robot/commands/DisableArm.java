package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

/* This command disables the Arm to stop it from moving */
public class DisableArm extends Command {
    private final ArmSubsystem m_arm;

    public DisableArm(ArmSubsystem arm) {
        m_arm = arm;
    }

    @Override
    public void initialize() {
        // Disable the PID control on the Arm
        m_arm.disable();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
