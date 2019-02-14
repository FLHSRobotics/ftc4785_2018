package org.firstinspires.ftc.teamcode.sandbox.stages;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.commands.MecanumDrive;
import org.firstinspires.ftc.teamcode.commands.TensorflowSampling;

@Autonomous(name="Sampling Auto",group="debug OpMode")

public class SamplingAuto extends LinearOpMode{

    private MecanumDrive mecanumDrive;
    private TensorflowSampling tensorflowSampling;

    private boolean isGold,seenGold;
    private int tries;



    @Override
    public void runOpMode() throws InterruptedException{
        tensorflowSampling = new TensorflowSampling(hardwareMap,telemetry);
        mecanumDrive = new MecanumDrive(hardwareMap);


        /** Wait for the game to begin */
        telemetry.addData(">", "Inited");
        telemetry.update();
        waitForStart();


        if(opModeIsActive()){

            tensorflowSampling.activateTfod();

            mecanumDrive.stopMove();


            for(tries =0; tries < 3 && !seenGold ; ++tries){
                Thread.sleep(800);
                isGold = tensorflowSampling.checkGold();
                telemetry.addData("Gold detected?", isGold);
                telemetry.addData("Tries", tries);
                if(isGold && tensorflowSampling.getRecognitions() != null){
                    telemetry.addData("Gold seen?", seenGold);
                    mecanumDrive.moveFwd();
                    Thread.sleep(500);
                    mecanumDrive.stopMove();
                    seenGold = isGold;
                    tensorflowSampling.disableTfod();
                    mecanumDrive.moveRvr();
                    Thread.sleep(500);
                    mecanumDrive.stopMove();
                }else if(tries > 0){
                    mecanumDrive.moveParaLeft();
                    Thread.sleep(2000);
                    mecanumDrive.stopMove();
                }
                telemetry.update();
            }

            if(tries == 1){
                mecanumDrive.moveParaLeft();
                Thread.sleep(7500);
                mecanumDrive.stopMove();
            }else if(tries == 2){
                mecanumDrive.moveParaLeft();
                Thread.sleep(6000);
                mecanumDrive.stopMove();
            }else{
                mecanumDrive.moveParaLeft();
                Thread.sleep(4500);
                mecanumDrive.stopMove();
            }

            mecanumDrive.rightConerning();
            Thread.sleep(1500);
            mecanumDrive.moveRvr();
            Thread.sleep(700);
            mecanumDrive.stopMove();

            telemetry.update();
        }
    }


}