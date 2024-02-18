// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
    // Left and Right motors
    private final PWMSparkMax m_armMotorLeft = new PWMSparkMax(ArmConstants.kPortPWM_ArmLift_L);
    private final PWMSparkMax m_armMotorRight = new PWMSparkMax(ArmConstants.kPortPWM_ArmLift_R);

    // Default max output is 100%
    private double m_maxOutput = 1.0;

    public ArmSubsystem() {
        // Motors facing different directions
        m_armMotorRight.setInverted(true);
    }

    public void liftArm(double speed) {
        // Both motors need to move at the same speed
        speed = speed * m_maxOutput;
        m_armMotorLeft.set(speed);
        m_armMotorRight.set(speed);
    }

    /* Limit the maximum output of the motors -> for testing mostly */
    public void setMaxOutput(double maxOutput) {
        m_maxOutput = maxOutput;
    }
}
