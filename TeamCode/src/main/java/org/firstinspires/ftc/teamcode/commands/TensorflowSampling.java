package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

public class TensorflowSampling {

    /**
     * Vuforia and Tensorflow
     */
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private List<Recognition> updatedRecognitions;

    private Telemetry telemetry;
    private HardwareMap hardwareMap;


    private static final String VUFORIA_KEY = "AVCNSTD/////AAABmTwdHcJUL0A9vJepRybq2gMBX64uJ5fQZePzyasRKU3rcwWbBLXvtczSuR4kWsswpiYDdaQSBHwi4wJxvJE4Kq+ttr0ZEmehKbhWf9eFlaCDuhYYvnTPwLoj+xIss4YPsXUOdExoDamoFR+PTZjRUa3Shf+t56/eggATK/4aF+1C1B/1VP+jArEz2VobuZQ7DhXuH2PKK7ufYVcshWJh7NsJxhuYyNSSzH6MsOpAiKg/lmPl4TAewXXbTxaRYiQfN/shUScZc/3fBiHwaozc8Ix8qwlDPBymFtyoV9wstWnzh3sEzbnkUOCeVkLsVVCPbhfgH5s2siF724LuWFCzrmh757i1cjbGLMNbtI3qNaEe";

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";


    public TensorflowSampling(
            HardwareMap hardwareMap,
            Telemetry telemetry
    ){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        setupVuforia();
    }

    private void setupVuforia(){
        initVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
    }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName   = hardwareMap.get(WebcamName.class,"Webcam 1");
        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public List<Recognition> getRecognitions(){
        return updatedRecognitions;
    }

    public void activateTfod(){
        if (tfod != null) {
            tfod.activate();
        }
    }

    public void disableTfod(){
        if (tfod != null) {
            tfod.shutdown();
        }
    }

    public boolean checkGold(){
        updatedRecognitions = tfod.getUpdatedRecognitions();
        if (updatedRecognitions != null) {
            telemetry.addData("# Object Detected", updatedRecognitions.size());
            for (Recognition recognition : updatedRecognitions) {
                telemetry.addData("Mineral Seen",recognition.getLabel());
                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                    return true;//Seen Gold
                }
            }
            return false;//Didn't see gold
        }else{
            return false;//List not even initalized
        }
    }


}
