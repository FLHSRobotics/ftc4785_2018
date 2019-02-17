package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class MecanumDrive{

    private DcMotorEx leftFrontDrive,rightFrontDrive,leftRearDrive,rightRearDrive;
    private HardwareMap hardwareMap;

    private double power = 0.68;

    public MecanumDrive(
            HardwareMap hardwareMap
    ){
        this.hardwareMap = hardwareMap;
        setupDrive();
    }

    public void setPower(double power){
        this.power = power;

    }

    private void setupDrive(){
        leftFrontDrive  = hardwareMap.get(DcMotorEx.class, "left_front_drive");
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, "right_front_drive");
        leftRearDrive = hardwareMap.get(DcMotorEx.class, "left_rear_drive");
        rightRearDrive = hardwareMap.get(DcMotorEx.class,"right_rear_drive");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);

        this.resetEndoer();
        this.setRunToPosition();

    }

    public void moveFwd(int position){
        this.resetEndoer();
        this.setRunToPosition();
        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(power);
        this.leftFrontDrive.setTargetPosition(position);
        this.leftRearDrive.setTargetPosition(position);
        this.rightFrontDrive.setTargetPosition(position);
        this.rightRearDrive.setTargetPosition(position);

    }

    public void moveRvr(int position){
        this.resetEndoer();
        this.setRunToPosition();

        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(power);
        this.leftFrontDrive.setTargetPosition(-position);
        this.leftRearDrive.setTargetPosition(-position);
        this.rightFrontDrive.setTargetPosition(-position);
        this.rightRearDrive.setTargetPosition(-position);
    }

    public void moveParaLeft(int position){
        this.resetEndoer();
        this.setRunToPosition();

        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(power);

        this.leftFrontDrive.setTargetPosition(position);
        this.leftRearDrive.setTargetPosition(-position);
        this.rightFrontDrive.setTargetPosition(-position);
        this.rightRearDrive.setTargetPosition(position);

    }

    public void moveParaRight(int position){
        this.resetEndoer();
        this.setRunToPosition();

        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(power);

        this.leftFrontDrive.setTargetPosition(-position);
        this.leftRearDrive.setTargetPosition(position);
        this.rightFrontDrive.setTargetPosition(position);
        this.rightRearDrive.setTargetPosition(-position);

    }


    public void stopMove(){
        this.resetEndoer();
        this.leftFrontDrive.setPower(0);
        this.leftRearDrive.setPower(0);
        this.rightFrontDrive.setPower(0);
        this.rightRearDrive.setPower(0);
    }

    public void rightConerning(int position){
        this.resetEndoer();
        this.setRunToPosition();

        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(power);

        this.leftFrontDrive.setTargetPosition(0);
        this.leftRearDrive.setTargetPosition(0);
        this.rightFrontDrive.setTargetPosition(position);
        this.rightRearDrive.setTargetPosition(position);

    }

    public void leftConerning(int position){
        this.resetEndoer();
        this.setRunToPosition();

        this.leftFrontDrive.setTargetPosition(position);
        this.leftRearDrive.setTargetPosition(position);
        this.rightFrontDrive.setTargetPosition(0);
        this.rightRearDrive.setTargetPosition(0);

        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(power);
    }

    public void turnAround(int position){
        this.resetEndoer();
        this.setRunToPosition();

        this.leftFrontDrive.setTargetPosition(position);
        this.leftRearDrive.setTargetPosition(position);
        this.rightFrontDrive.setTargetPosition(-position);
        this.rightRearDrive.setTargetPosition(-position);

        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(power);
    }

    private void resetEndoer(){
        this.leftFrontDrive.setMode(DcMotor.RunMode.RESET_ENCODERS);
        this.leftRearDrive.setMode(DcMotor.RunMode.RESET_ENCODERS);
        this.rightFrontDrive.setMode(DcMotor.RunMode.RESET_ENCODERS);
        this.rightRearDrive.setMode(DcMotor.RunMode.RESET_ENCODERS);

    }

    private void setRunToPosition(){
        this.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.leftRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.leftFrontDrive.setTargetPositionTolerance(25);
        this.rightFrontDrive.setTargetPositionTolerance(25);
        this.leftRearDrive.setTargetPositionTolerance(25);
        this.rightRearDrive.setTargetPositionTolerance(25);
    }

    public boolean isMotorBusy(){
        return this.leftFrontDrive.isBusy() || this.leftRearDrive.isBusy() || this.rightFrontDrive.isBusy() || this.rightRearDrive.isBusy();
    }

}