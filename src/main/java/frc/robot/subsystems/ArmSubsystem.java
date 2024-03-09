// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
    // Left and Right motors are brushless NEO motors connected using CAN
    private final CANSparkMax m_armLiftMotor_Left = new CANSparkMax(ArmConstants.kCANid_ArmLift_L,
            MotorType.kBrushless);
    private final CANSparkMax m_armLiftMotor_Right = new CANSparkMax(ArmConstants.kCANid_ArmLift_R,
            MotorType.kBrushless);

    public ArmSubsystem() {
        // Arm lift motors are facing different directions so reverse one of them
        m_armLiftMotor_Right.setInverted(true);
    }

    public void liftArm(double speed) {
        // Both motors need to move at the same speed
        speed = speed * ArmConstants.kArmAngleSpeed; // modify speed based on max output constant
        m_armLiftMotor_Left.set(speed);
        m_armLiftMotor_Right.set(speed);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.setSmartDashboardType("Arm Subsystem");
    }
}
