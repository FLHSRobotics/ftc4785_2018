package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class MecanumDrive{

    private DcMotor leftFrontDrive,rightFrontDrive,leftRearDrive,rightRearDrive;
    private HardwareMap hardwareMap;

    private double power = 0.5;

    public MecanumDrive(
            HardwareMap hardwareMap
    ){
        this.hardwareMap = hardwareMap;
        setupDrive();
    }

    private void setupDrive(){
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        leftRearDrive = hardwareMap.get(DcMotor.class, "left_rear_drive");
        rightRearDrive = hardwareMap.get(DcMotor.class,"right_rear_drive");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void moveFwd(){
        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(power);
    }

    public void moveRvr(){
        this.leftFrontDrive.setPower(-power);
        this.leftRearDrive.setPower(-power);
        this.rightFrontDrive.setPower(-power);
        this.rightRearDrive.setPower(-power);
    }

    public void moveParaLeft(){
        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(-power);
        this.rightFrontDrive.setPower(-power);
        this.rightRearDrive.setPower(power);
    }

    public void moveParaRight(){
        this.leftFrontDrive.setPower(-power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(-power);
    }


    public void stopMove(){
        this.leftFrontDrive.setPower(0);
        this.leftRearDrive.setPower(0);
        this.rightFrontDrive.setPower(0);
        this.rightRearDrive.setPower(0);
    }

    public void rightConerning(){
        this.leftFrontDrive.setPower(0);
        this.leftRearDrive.setPower(0);
        this.rightFrontDrive.setPower(power);
        this.rightRearDrive.setPower(power);
    }

    public void leftConerning(){
        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(0);
        this.rightRearDrive.setPower(0);
    }

    public void turnAround(){
        this.leftFrontDrive.setPower(power);
        this.leftRearDrive.setPower(power);
        this.rightFrontDrive.setPower(-power);
        this.rightRearDrive.setPower(-power);
    }
}