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
    private final CANSparkMax m_armLiftMotor_Left = new CANSparkMax(ArmConstants.kCANid_ArmLift_L, MotorType.kBrushless);
    private final CANSparkMax m_armLiftMotor_Right = new CANSparkMax(ArmConstants.kCANid_ArmLift_R, MotorType.kBrushless);
    
    // Motor to spin the intake wheels
    private final CANSparkMax m_intakeMotor = new CANSparkMax(ArmConstants.kCANid_Intake, MotorType.kBrushless);

    // Motors to spin the note-throwing wheels
    private final CANSparkMax m_throwerWheelsMotor_Top = new CANSparkMax(ArmConstants.kCANid_ThrowerWheels_Top, MotorType.kBrushless);
    private final CANSparkMax m_throwerWheelsMotor_Bottom = new CANSparkMax(ArmConstants.kCANid_ThrowerWheels_Bottom, MotorType.kBrushless);

    // Default max output is 100%
    private double m_armSpeedMultiplier = 1.0;

    public ArmSubsystem() {
        // Arm lift motors are facing different directions so reverse one of them
        m_armLiftMotor_Right.setInverted(true);

        // Thrower wheel motors need to spin in opposite directions so reverse one of them
        m_throwerWheelsMotor_Bottom.setInverted(true);
    }

    public void liftArm(double speed) {
        // Both motors need to move at the same speed
        speed = speed * m_armSpeedMultiplier;
        m_armLiftMotor_Left.set(speed);
        m_armLiftMotor_Right.set(speed);
    }

    /* Limit the maximum output of the motors -> for testing mostly */
    public void setSpeedMultiplier(double speed) {
        m_armSpeedMultiplier = speed;
    }

    public void activateIntake(){
        m_intakeMotor.set(0.25);
    }

    public void deactivateIntake(){
        m_intakeMotor.set(0);
    }

    /* Spin the thrower wheels at their current speed factor */
    public void spinThrowers() {
        double throwerSpeed;
        throwerSpeed = 0.5; // TODO(malik): set up variable thrower wheel speeds
        m_throwerWheelsMotor_Bottom.set(throwerSpeed);
        m_throwerWheelsMotor_Top.set(throwerSpeed);
    }

    /* Stop the thrower wheels from spinning */
    public void stopThrowers() {
        m_throwerWheelsMotor_Bottom.set(0);
        m_throwerWheelsMotor_Top.set(0);
    }
}
