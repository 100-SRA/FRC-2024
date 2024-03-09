package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Units;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    // Left and Right sides of drivetrain
    private final PWMSparkMax m_leftDrive = new PWMSparkMax(DriveConstants.kPortPWM_Drivetrain_L);
    private final PWMSparkMax m_rightDrive = new PWMSparkMax(DriveConstants.kPortPWM_Drivetrain_R);

    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftDrive::set, m_rightDrive::set);

    private boolean m_IsReversed = false;
    private double m_SpeedMultiplier = 1.0;
    
    // Left and right side drive encoders
    private final Encoder m_encoderLeft = new Encoder(
        DriveConstants.kEncoderPorts_Left[0],
        DriveConstants.kEncoderPorts_Left[1],
        DriveConstants.kEncoderReversed_Left);
    private final Encoder m_encoderRight = new Encoder(
        DriveConstants.kEncoderPorts_Right[0],
        DriveConstants.kEncoderPorts_Right[1],
        DriveConstants.kEncoderReversed_Right);

    // Gyroscope
    private final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();

    /* Create new drive subsystem */
    public DriveSubsystem() {
        /* Add info to the dashboard about the drive train */
        SendableRegistry.addChild(m_drive, m_leftDrive);
        SendableRegistry.addChild(m_drive, m_rightDrive);
        SendableRegistry.addChild(m_drive, m_encoderLeft);
        SendableRegistry.addChild(m_drive, m_encoderRight);

        /* Invert right side motors */
        m_rightDrive.setInverted(true);

        // Set distance per pulse for the encoders
        m_encoderLeft.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
        m_encoderRight.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);

        resetEncoders();
    }

    public Measure<Distance> getMeanEncoderDistance() {
        // Get the mean (average) distance between the two encoders
        Measure<Distance> leftDistance = Units.Meters.of(m_encoderLeft.getDistance());
        Measure<Distance> rightDistance = Units.Meters.of(m_encoderRight.getDistance());
        Measure<Distance> averageDistance = leftDistance.plus(rightDistance).divide(2.0);
        return averageDistance;
    }

    public void resetEncoders() {
        /* Reset the encoders' values to 0 */
        m_encoderLeft.reset();
        m_encoderRight.reset();
    }

    /**
     * Drive the robot using arcade controls
     * 
     * @param forward  commanded forward movement
     * @param rotation commanded rotation movement
     */
    public void arcadeDrive(double forward, double rotation) {
        if (m_IsReversed) {
            forward = forward * -1;
        }
        forward = forward * m_SpeedMultiplier;
        rotation = rotation * m_SpeedMultiplier;
        m_drive.arcadeDrive(forward, rotation);
    }

    public void toggleReversed() {
        m_IsReversed = !m_IsReversed;
    }

    public void setSpeedMultiplier(double speed) {
        m_SpeedMultiplier = speed;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.setSmartDashboardType("Drive Subsystem");
        // Publish encoder states to telemetry
        builder.addDoubleProperty("left drive encoder", () -> m_encoderLeft.getDistance(), null);
        builder.addDoubleProperty("right drive encoder", () -> m_encoderRight.getDistance(), null);
    }
}
