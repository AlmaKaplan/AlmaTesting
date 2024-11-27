
package frc.robot.subsystem.Shooter;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ma5951.utils.DashBoard.MAShuffleboard;
import com.ma5951.utils.Utils.ConvUtil;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Shooter extends SubsystemBase {
  public static Shooter shooter;

  private MAShuffleboard board;

  private TalonFX motor_R;
  private TalonFX motor_L;
  private TalonFXConfiguration motorConfig;

  private StatusSignal<Current> currentDrawMotor_L;
  private StatusSignal<AngularVelocity> velocityMotor_L;
  private StatusSignal<Temperature> motorTempMotor_L;
  private StatusSignal<Voltage> appliedVoltageMotor_L;
  private StatusSignal<Angle> positionMotor_L;

  private StatusSignal<Current> currentDrawMotor_R;
  private StatusSignal<AngularVelocity> velocityMotor_R;
  private StatusSignal<Temperature> motorTempMotor_R;
  private StatusSignal<Voltage> appliedVoltageMotor_R;
  private StatusSignal<Angle> positionMotor_R;


  private Shooter() {
    motor_R = new TalonFX(PortMap.Shooter.SHOOTER_MOTOR_R);
    motor_L = new TalonFX(PortMap.Shooter.SHOOTER_MOTOR_L);
    motorConfig = new TalonFXConfiguration();

    currentDrawMotor_L = motor_L.getSupplyCurrent();
    velocityMotor_L =  motor_L.getVelocity();
    motorTempMotor_L = motor_L.getDeviceTemp();
    appliedVoltageMotor_L = motor_L.getMotorVoltage();
    positionMotor_L = motor_L.getPosition();

    currentDrawMotor_R = motor_R.getSupplyCurrent();
    velocityMotor_R =  motor_R.getVelocity();
    motorTempMotor_R = motor_R.getDeviceTemp();
    appliedVoltageMotor_R = motor_R.getMotorVoltage();
    positionMotor_R = motor_R.getPosition();

    board = new MAShuffleboard("Shooter");


    Motor_L_Config();
    Motor_R_Config();
  }

  private void Motor_L_Config() {
    
    motorConfig.CurrentLimits.StatorCurrentLimitEnable = ShooterConstance.ENABLE_CURRENT_LIMIT;
    motorConfig.CurrentLimits.SupplyCurrentLimit = ShooterConstance.CURRENT_LIMIT;
    motorConfig.CurrentLimits.SupplyCurrentLowerLimit = ShooterConstance.CONTINOUS_POWER_LIMIT;
    motorConfig.CurrentLimits.SupplyCurrentLowerTime = ShooterConstance.CONTINOUS_POWER_DURATION;

    motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    motorConfig.Voltage.PeakForwardVoltage = 12;
    motorConfig.Voltage.PeakReverseVoltage = -12;

    motor_L.getConfigurator().apply(motorConfig);
  }

  private void Motor_R_Config() {
    
    motorConfig.CurrentLimits.StatorCurrentLimitEnable = ShooterConstance.ENABLE_CURRENT_LIMIT;
    motorConfig.CurrentLimits.SupplyCurrentLimit = ShooterConstance.CURRENT_LIMIT;
    motorConfig.CurrentLimits.SupplyCurrentLowerLimit = ShooterConstance.CONTINOUS_POWER_LIMIT;
    motorConfig.CurrentLimits.SupplyCurrentLowerTime = ShooterConstance.CONTINOUS_POWER_DURATION;

    motorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    motorConfig.Voltage.PeakForwardVoltage = 12;
    motorConfig.Voltage.PeakReverseVoltage = -12;

    motor_R.getConfigurator().apply(motorConfig);
  }

  public void setVoltage(double volt) {
    motor_L.setVoltage(volt);
    motor_R.setVoltage(volt);
  }

  public double getLeftMotorCurrentDraw() {
    currentDrawMotor_L.refresh();
    return currentDrawMotor_L.getValueAsDouble();
  }


  public double getLeftMotorVelocity() {
    velocityMotor_L.refresh();
    return ConvUtil.RPStoRPM(velocityMotor_L.getValueAsDouble());
  }


  public double getLeftMotorTemp() {
    motorTempMotor_L.refresh();
    return motorTempMotor_L.getValueAsDouble();
  }


  public double getLeftMotorAppliedVoltage() {
    appliedVoltageMotor_L.refresh();
    return appliedVoltageMotor_L.getValueAsDouble();
  }


  public double getLeftMotorPosition() {
    positionMotor_L.refresh();
    return positionMotor_L.getValueAsDouble();
  }

  public double getRightMotorCurrentDraw() {
    currentDrawMotor_R.refresh();
    return currentDrawMotor_R.getValueAsDouble();
  }


  public double getRightMotorVelocity() {
    velocityMotor_R.refresh();
    return ConvUtil.RPStoRPM(velocityMotor_R.getValueAsDouble());
  }


  public double getRightMotorTemp() {
    motorTempMotor_R.refresh();
    return motorTempMotor_R.getValueAsDouble();
  }


  public double getRightMotorAppliedVoltage() {
    appliedVoltageMotor_R.refresh();
    return appliedVoltageMotor_R.getValueAsDouble();
  }


  public double getRightMotorPosition() {
    positionMotor_R.refresh();
    return positionMotor_R.getValueAsDouble();
  }

  public static Shooter getInstance() {
    if (shooter == null) {
      shooter = new Shooter();
    }
    return shooter;
  }



  @Override
  public void periodic() {
    board.addNum("Shooter left motor speed", getLeftMotorVelocity());
    board.addNum("Shhoter right motor speed", getRightMotorVelocity());
  }
}
