package frc.robot.POM_lib.Motors;

import java.util.ArrayList;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class POMTalonFX extends TalonFX implements POMMotor {

    static Orchestra orchestra = new Orchestra();

    static ArrayList<POMTalonFX> instances = new ArrayList<POMTalonFX>();
    TalonFXConfiguration config = new TalonFXConfiguration();

    public POMTalonFX(int id) {
        super(id);
        instances.add(this);
        config.Audio.AllowMusicDurDisable = true;
        getConfigurator().apply(config);
        orchestra.addInstrument(this);
        
    }
    
    @Override
    public void stop() {
        set(0);
    }
    
    @Override
    public void setDirection(Direction direction) {
        if (direction == Direction.ClockWise) {
            config.MotorOutput.withInverted(InvertedValue.Clockwise_Positive);
        } else {
            config.MotorOutput.withInverted(InvertedValue.CounterClockwise_Positive);
        }
        getConfigurator().apply(config);
    }

    
    @Override
    public void setBrake(boolean isBrake) {
        config.MotorOutput.withNeutralMode(isBrake ? NeutralModeValue.Coast : NeutralModeValue.Brake);
        getConfigurator().apply(config);

    }

    public static void EnableSound(){
        orchestra.loadMusic("enable.chrp");
        orchestra.play();
    }

    public static void DisableSound(){
        orchestra.loadMusic("disable.chrp");
        orchestra.play();
    }
    
    
}
