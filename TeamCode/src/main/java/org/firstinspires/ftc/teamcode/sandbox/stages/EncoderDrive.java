package org.firstinspires.ftc.teamcode.sandbox.stages;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.MecanumDrive;

@Autonomous(name = "Encoder Drive", group = "debug OpMode")
public class EncoderDrive extends LinearOpMode {

    private MecanumDrive mecanumDrive;


    @Override
    public void runOpMode() throws InterruptedException{

        mecanumDrive = new MecanumDrive(hardwareMap);


        telemetry.addData(">", "Inited");
        telemetry.update();
        waitForStart();

        mecanumDrive.moveFwd(1000);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();

        mecanumDrive.moveParaLeft(1000);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();


    }

}
