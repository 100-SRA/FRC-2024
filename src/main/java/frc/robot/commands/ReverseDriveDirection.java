package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class ReverseDriveDirection extends Command{
    private final DriveSubsystem m_Drivesubsystem;

    public ReverseDriveDirection(DriveSubsystem subsystem){
        m_Drivesubsystem = subsystem;
        addRequirements(m_Drivesubsystem);
    }

    @Override
    public void initialize(){
        m_Drivesubsystem.toggleReversed();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}