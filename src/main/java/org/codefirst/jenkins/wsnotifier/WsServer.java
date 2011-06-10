package org.codefirst.jenkins.wsnotifier;

import hudson.init.Initializer;
import hudson.init.InitMilestone;

class WsServer {
    @Initializer(before=InitMilestone.COMPLETED)
    public static void init() throws IOException {
        System.out.println("******************************");
    }
}
