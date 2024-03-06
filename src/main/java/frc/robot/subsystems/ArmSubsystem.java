// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends ProfiledPIDSubsystem {
    // Left and Right motors are brushless NEO motors connected using CAN
    private final CANSparkMax m_armLiftMotor_Left = new CANSparkMax(ArmConstants.kCANid_ArmLift_L,
            MotorType.kBrushless);
    private final CANSparkMax m_armLiftMotor_Right = new CANSparkMax(ArmConstants.kCANid_ArmLift_R,
            MotorType.kBrushless);

    // Duty cycle (absolute) encoder connected to the arm
    // The absolute encoder will enable us to get the absolute angle of the arm
    private final DutyCycleEncoder m_encoder = new DutyCycleEncoder(ArmConstants.kArmEncoderDIOPort);

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

        // Set up encoder to use degrees [0, 360] as angular distance rather than [0, 1]
        m_encoder.setDistancePerRotation(360.0);

        // Start the arm out at the initial position (vertical to remain within vertical bounding box of bumpers)
        setGoal(ArmConstants.kArmAngle_Start.magnitude());
    }

    @Override
    public void useOutput(double output, TrapezoidProfile.State setpoint) {
        // output holds the value coming out of the PID controller
        // setpoint holds the target position and velocity of the arm
        
        // TODO(Malik): determine if we need to use the setpoint value here or not
        m_armLiftMotor_Left.set(output);
        m_armLiftMotor_Right.set(output);
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
