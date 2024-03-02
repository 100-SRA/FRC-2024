package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.NoteIntakeConstants;

public class NoteIntakeSubsystem extends SubsystemBase {
    // Motor to spin the intake wheels
    private final CANSparkMax m_intakeMotor = new CANSparkMax(NoteIntakeConstants.kCANid_Intake, MotorType.kBrushless);

    public void activateIntake() {
        m_intakeMotor.set(NoteIntakeConstants.kIntakePower);
    }

    public void deactivateIntake() {
        m_intakeMotor.set(0);
    }
}
