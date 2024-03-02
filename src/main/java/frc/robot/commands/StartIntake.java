package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class StartIntake extends Command{
    private final ArmSubsystem m_ArmSubsystem;
    
    public StartIntake(ArmSubsystem subsystem){
        m_ArmSubsystem = subsystem;
        addRequirements(m_ArmSubsystem);
    }

    /*called the activate intake for the arm subsystem, once called it runs until the button is pressed to stop */
    @Override 
    public void initialize(){
        m_ArmSubsystem.activateIntake();
    }

    /* ensuring that the command caller knows that the function has been ran. another function that ensures the motors completely stop spinning once the button is pressed again.*/
    @Override 
    public boolean isFinished(){
        return true; 
    } 

    /* following the structure of the command properties overriding the interrupted func. */
    @Override
    public void end(boolean interrupted){
        m_ArmSubsystem.deactivateIntake();
    } 

} 