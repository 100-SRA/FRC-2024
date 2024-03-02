package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.NoteThrowerConstants;

public class NoteThrowerSubsystem extends SubsystemBase {
    // Motors to spin the note-throwing wheels
    // TODO: switch these two motor types to brushless once we do so on the physical
    // robot
    // private final CANSparkMax m_throwerWheelsMotor_Top = new
    // CANSparkMax(ArmConstants.kCANid_ThrowerWheels_Top, MotorType.kBrushed);
    // private final CANSparkMax m_throwerWheelsMotor_Bottom = new
    // CANSparkMax(ArmConstants.kCANid_ThrowerWheels_Bottom, MotorType.kBrushed);
    private final PWMSparkMax m_throwerWheelsMotor_Top = new PWMSparkMax(
            NoteThrowerConstants.kPortPWM_ThrowerWheels_Top);
    private final PWMSparkMax m_throwerWheelsMotor_Bottom = new PWMSparkMax(
            NoteThrowerConstants.kPortPWM_ThrowerWheels_Bottom);

    public NoteThrowerSubsystem() {
        // Thrower wheel motors need to spin in opposite directions so reverse one of
        // them
        m_throwerWheelsMotor_Bottom.setInverted(true);
    }

    /* Spin the thrower wheels at their current speed factor */
    public void spinThrowers() {
        double throwerSpeed;
        throwerSpeed = 0.5; // TODOs(malik): set up variable thrower wheel speeds
        m_throwerWheelsMotor_Bottom.set(throwerSpeed);
        m_throwerWheelsMotor_Top.set(throwerSpeed);
    }

    /* Stop the thrower wheels from spinning */
    public void stopThrowers() {
        m_throwerWheelsMotor_Bottom.set(0);
        m_throwerWheelsMotor_Top.set(0);
    }
}
