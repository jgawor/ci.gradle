/**
 * (C) Copyright IBM Corporation 2014, 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.wasdev.wlp.gradle.plugins.extensions

import org.gradle.util.ConfigureUtil

class LibertyExtension {
    
    String wlpDir
    String outputDir
    String userDir
    String serverName = "defaultServer"

    boolean clean = false
    String timeout
    String template

    def numberOfClosures = 0    

    FeatureExtension features = new FeatureExtension()
    UninstallFeatureExtension uninstallfeatures = new UninstallFeatureExtension()
    InstallExtension install = new InstallExtension()

    DeployExtension deploy = new DeployExtension()
    UndeployExtension undeploy = new UndeployExtension()

    PackageAndDumpExtension packageLiberty = new PackageAndDumpExtension()
    PackageAndDumpExtension dumpLiberty = new PackageAndDumpExtension()
    PackageAndDumpExtension javaDumpLiberty = new PackageAndDumpExtension()

    def uninstallfeatures(Closure closure) {
	ConfigureUtil.configure(closure, uninstallfeatures)
    }

    def features(Closure closure) {
        ConfigureUtil.configure(closure, features)
    }

    def install(Closure closure) {
        ConfigureUtil.configure(closure, install)
    }

    def deploy(Closure closure) {
        if (numberOfClosures > 0){
            deploy.listOfClosures.add(deploy.clone())
            deploy.file = null
        }
        ConfigureUtil.configure(closure, deploy)
        numberOfClosures++
    }

    def undeploy(Closure closure) {
        ConfigureUtil.configure(closure, undeploy)
    }
    
    def packageLiberty(Closure closure) {
        ConfigureUtil.configure(closure, packageLiberty)
    }

    def dumpLiberty(Closure closure) {
        ConfigureUtil.configure(closure, dumpLiberty)
    }

    def javaDumpLiberty(Closure closure) {
        ConfigureUtil.configure(closure, javaDumpLiberty)
    }
}
