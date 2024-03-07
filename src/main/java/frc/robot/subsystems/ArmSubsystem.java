// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends ProfiledPIDSubsystem {
    // Left and Right motors are brushless NEO motors connected using CAN
    private final CANSparkMax m_armLiftMotor_Left = new CANSparkMax(ArmConstants.kCANid_ArmLift_L,
            MotorType.kBrushless);
    private final CANSparkMax m_armLiftMotor_Right = new CANSparkMax(ArmConstants.kCANid_ArmLift_R,
            MotorType.kBrushless);

    // Quadrature (relative) encoder connected to the arm
    // Relative encoders start out at a reading of 0.0 and reset to 0.0 every time you call reset()
    // They measure change in rotation from the 0 point
    private final Encoder m_encoder = new Encoder(ArmConstants.kArmEncoderDIOPortA, ArmConstants.kArmEncoderDIOPortB);

    public ArmSubsystem() {
        super(
            new ProfiledPIDController(
                ArmConstants.kPID_proportional /* Kp constant */,
                ArmConstants.kPID_integral /* Ki constant */,
                ArmConstants.kPID_derivative /* Kd constant */,
                new TrapezoidProfile.Constraints(
                    ArmConstants.kArmMaxVelocity /* max velocity */,
                    ArmConstants.kArmMaxAcceleration /* max acceleration */
                )
            ),
            0 /* starting position */
        );
        // Arm lift motors are facing different directions so reverse one of them
        m_armLiftMotor_Right.setInverted(true);

        // Set the encoder to output angular distance in degrees
        m_encoder.setDistancePerPulse(ArmConstants.kArmEncoderDegreesPerCycle);

        // Start the arm out at the initial position (vertical to remain within vertical bounding box of bumpers)
        setGoal(ArmConstants.kArmAngle_Start.magnitude());
    }

    @Override
    public void useOutput(double output, TrapezoidProfile.State setpoint) {
        // output holds the value coming out of the PID controller
        // setpoint holds the target position and velocity of the arm
        
        // TODO(Malik): determine if we need to use the setpoint value here or not
        double simpleFeedForward = Math.max(Math.min(getMeasurement() - setpoint.position, 0.1), -0.1);
        m_armLiftMotor_Left.set(output + simpleFeedForward);
        m_armLiftMotor_Right.set(output + simpleFeedForward);
    }

    @Override
    public double getMeasurement() {
        // Read the angle of the encoder mapped between 0deg and 360deg
        return m_encoder.getDistance();
    }

    // TODO(Malik): clear this out once we have preset positions
    public void liftArm(double speed) {
        // Both motors need to move at the same speed
        speed = speed * ArmConstants.kArmAngleSpeed; // modify speed based on max output constant
        m_armLiftMotor_Left.set(speed);
        m_armLiftMotor_Right.set(speed);
    }
}
