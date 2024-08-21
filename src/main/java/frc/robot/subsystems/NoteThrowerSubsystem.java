package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.NoteIntakeConstants;
import frc.robot.Constants.NoteThrowerConstants;

public class NoteThrowerSubsystem extends SubsystemBase {
    // Motors to spin the note-throwing wheels
    // TODO: switch these two motor types to brushless once we do so on the physical
    // robot
    // private final CANSparkMax m_throwerWheelsMotor_Top = new
    // CANSparkMax(ArmConstants.kCANid_ThrowerWheels_Top, MotorType.kBrushed);
     //private final CANSparkMax m_throwerWheelsMotor_Bottom = new
     //CANSparkMax(ArmConstants.kCANid_ThrowerWheels_Bottom, MotorType.kBrushed);
    //private final PWMSparkMax m_throwerWheelsMotor_Top = new PWMSparkMax(
            //NoteThrowerConstants.kPortPWM_ThrowerWheels_Top);
    //private final PWMSparkMax m_throwerWheelsMotor_Bottom = new PWMSparkMax(
           //NoteThrowerConstants.kPortPWM_ThrowerWheels_Bottom);
    
    private final CANSparkMax m_throwerWheelsMotor_Bottom = new CANSparkMax(NoteThrowerConstants.kCANid_ThrowerWheels_Top, MotorType.kBrushless);
    private final CANSparkMax m_throwerWheelsMotor_Top = new CANSparkMax(NoteThrowerConstants.kCANid_ThrowerWheels_Bottom, MotorType.kBrushless);
    
    /* Variable speed for better control of shooting */
    private double m_speed = 1.0;

    /* Constructor to invert both the thrower wheel motors */
    public NoteThrowerSubsystem() {
        m_throwerWheelsMotor_Bottom.setInverted(true);
        m_throwerWheelsMotor_Top.setInverted(true);
    }

    /* Spin the thrower wheels at their current speed factor */
    public void spinThrowers() {
        m_throwerWheelsMotor_Bottom.set(m_speed);
        m_throwerWheelsMotor_Top.set(m_speed);
    }

    /* Stop the thrower wheels from spinning */
    public void stopThrowers() {
        m_throwerWheelsMotor_Bottom.set(0);
        m_throwerWheelsMotor_Top.set(0);
    }

    /* Modify the launch speed of the wheels */
    public void setSpeed(double speed) {
        m_speed = speed;
    }
}
