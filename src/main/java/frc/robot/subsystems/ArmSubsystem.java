// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
    // Left and Right motors are brushless NEO motors connected using CAN
    private final CANSparkMax m_armMotorLeft = new CANSparkMax(ArmConstants.kIdCAN_ArmLift_L, MotorType.kBrushless);
    private final CANSparkMax m_armMotorRight = new CANSparkMax(ArmConstants.kIdCAN_ArmLift_R, MotorType.kBrushless);

    // Default max output is 100%
    private double m_speedMultiplier = 1.0;

    public ArmSubsystem() {
        // Motors facing different directions
        m_armMotorRight.setInverted(true);
    }

    public void liftArm(double speed) {
        // Both motors need to move at the same speed
        speed = speed * m_speedMultiplier;
        m_armMotorLeft.set(speed);
        m_armMotorRight.set(speed);
    }

    /* Limit the maximum output of the motors -> for testing mostly */
    public void setSpeedMultiplier(double speed) {
        m_speedMultiplier = speed;
    }
}
