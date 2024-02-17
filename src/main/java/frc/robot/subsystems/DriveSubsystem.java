package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    // Left and Right sides of drivetrain
    private final PWMSparkMax m_leftDrive = new PWMSparkMax(0);
    private final PWMSparkMax m_rightDrive = new PWMSparkMax(1);

    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftDrive::set, m_rightDrive::set);

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
        m_drive.arcadeDrive(forward, rotation);
    }

    /* TODO(malik): consider adding a control to change the max speed */
}
