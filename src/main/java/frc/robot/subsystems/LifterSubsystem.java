// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.LiftConstants;;

public class LifterSubsystem extends SubsystemBase {
    // Motor is a brushless NEO motors connected using CAN
    private final CANSparkMax m_liftMotor = new CANSparkMax(LiftConstants.kCANid_LiftMotor,
            MotorType.kBrushless);

    // Move the hook
    public void moveHook(double power) {
        // m_liftMotor.set(power);
        boolean reverse = power < 0;
        m_liftMotor.set(reverse ? -0.5 : 0.5);
    }

    public void stopHook() {
        m_liftMotor.set(0.0);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.setSmartDashboardType("Lifter Subsystem");
    }
}
