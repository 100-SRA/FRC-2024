package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableRegistry;
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
    // TODO(malik): add encoder setup here

    /* Create new drive subsystem */
    public DriveSubsystem() {
        /* Add info to the dashboard about the drive train */
        SendableRegistry.addChild(m_drive, m_leftDrive);
        SendableRegistry.addChild(m_drive, m_rightDrive);

        /* Invert right side motors */
        m_rightDrive.setInverted(true);
    }

    /**
     * Drive the robot using arcade controls
     * 
     * @param forward commanded forward movement
     * @param rotation commanded rotation movement
     */
    public void arcadeDrive(double forward, double rotation) {
        if (m_IsReversed){
            forward = forward * -1;
        }
        forward = forward * m_SpeedMultiplier;
        rotation = rotation * m_SpeedMultiplier;
        m_drive.arcadeDrive(forward, rotation);
        
    }

    public void toggleReversed(){
        m_IsReversed = !m_IsReversed;
    }
    public void setSpeedmultiplier (double speed) {
        m_SpeedMultiplier = speed;
    }
}
 