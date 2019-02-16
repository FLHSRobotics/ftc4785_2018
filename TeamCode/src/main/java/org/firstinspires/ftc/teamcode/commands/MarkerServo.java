package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MarkerServo {

    private HardwareMap hardwareMap;
    private Servo markerServo;
    private double servoDelta;

    public MarkerServo(
            HardwareMap hardwareMap
    ){
        this.hardwareMap = hardwareMap;
        this.setupMarkerServo();
    }

    private void setupMarkerServo(){
        markerServo = hardwareMap.get(Servo.class,"marker_servo");
    }

    public void rotateServo(double servoDirection){
        markerServo.setPosition(servoDirection);
    }

    public void stopServo(){
        markerServo.setPosition(0.5);
    }

}
