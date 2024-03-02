package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.NoteIntakeConstants;

public class NoteIntakeSubsystem extends SubsystemBase {
    // Motor to spin the intake wheels
    private final CANSparkMax m_intakeMotor = new CANSparkMax(NoteIntakeConstants.kCANid_Intake, MotorType.kBrushless);

    private boolean reversed = false;

    /* Run the intake wheel motor at the given speed, reversing if necessary */
    public void activateIntake() {
        double speed = NoteIntakeConstants.kIntakePower;
        if (reversed) {
            speed = speed * -1;
        }
        m_intakeMotor.set(speed);
    }

    /* Return the intake wheels to standby */
    public void deactivateIntake() {
        m_intakeMotor.set(0);
    }

    /* Flip the intake wheel direction */
    public void toggleDirection() {
        reversed = !reversed;
    }
}
