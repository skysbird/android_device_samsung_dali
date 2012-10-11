## Build Instructions for E120L tnx skysbird for code i just do almost nothing

### Prepare Build environment

```
sudo apt-get install git-core gnupg flex bison python rar original-awk gawk p7zip-full gperf libsdl1.2-dev libesd0-dev libwxgtk2.6-dev squashfs-tools build-essential zip curl libncurses5-dev zlib1g-dev pngcrush schedtool
sudo apt-get install libc6-dev x11proto-core-dev libx11-dev libgl1-mesa-dev mingw32 tofrodos python-markdown libxml2-utils
sudo apt-get install g++-multilib lib32z1-dev ia32-libs lib32ncurses5-dev lib32readline-gplv2-dev gcc-multilib g++-multilib xsltproc
```

Download Java JDK for Linux 64-bit from Java site: (http://www.oracle.com/technetwork/ja...ads/index.html)

```
sudo apt-get purge openjdk-\* icedtea-\* icedtea6-\*
sudo mkdir -p /opt/java/64/
sudo cp jdk-6u##-linux-x64.bin /opt/java/64
sudo su -
cd /opt/java/64
chmod +x jdk-6u##-linux-x64.bin
./jdk-6u##-linux-x64.bin
exit
```

```
gedit ~/.bashrc
```

```
# Java PATHs
export JAVA_HOME=/opt/java/64/jdk1.6.0_##
export PATH=$PATH:$JAVA_HOME/bin
```


### Follow the usual instructions to download sources for CM10, e.g.
```
1) mkdir ~/android/system
2) cd ~/android/system
3) curl https://dl-ssl.google.com/dl/googlesource/git-repo/repo > ~/bin/repo
4) chmod a+x ~/bin/repo
5) repo init -u git://github.com/CyanogenMod/android.git -b jellybean
```
Before that you may need to install some binaries, but those are the basic instructions. Google for the full setup details. If you had already set up an environment for CM9, you'll need to add this for CM10: "sudo apt-get install libxml2-utils"

Here's a good guide for Jelly Bean: http://forum.xda-developers.com/showthread.php?t=1762641.

Remain in ~/android/system for the rest of the commands.

### Include the file .repo/local_manifest.xml to allow these additional repositories to be synced:
```
<?xml version="1.0" encoding="UTF-8"?>
<manifest>
  <remote fetch="http://github.com/" name="gh" revision="master" />
  <project name="CyanogenMod/android_device_samsung_msm8660-common" path="device/samsung/msm8660-common" remote="github" revision="jellybean" />
  <project name="Socim/android_kernel_samsung_msm8660-common" path="kernel/samsung/msm8660-common" revision="jellybean" />
  <project name="Socim/android_bootable_recovery" path="bootable/recovery" revision="jellybean" />
  <project name="Socim/android_packages_apps_Camera" path="packages/apps/Camera" revision="jellybean" />
  <project name="Socim/android_device_samsung_dali" path="device/samsung/dali" revision="jellybean" />
  <project name="Socim/android_vendor_samsung_dali" path="vendor/samsung/dali" revision="jellybean" />
</manifest>
```
NOTE: Under the kernel folder you need to add "arch/arm/configs/cyanogenmod_dali_defconfig".  Download the file from here:  https://raw.github.com/dsixda/android_kernel_samsung_msm8660-common/jellybean/arch/arm/configs/cyanogenmod_dali_defconfig


### Download or update all repositories:
```
repo sync -j16   
```



### Get all the prebuilts, like ROM Manager:
```
vendor/cm/get-prebuilts
```

### You might need to update your cross-compiler path:
```
1) Open up kernel/samsung/msm8660-common/Makefile
2) Edit the line starting with 'CROSS-COMPILE' to point to: 
     /home/<your user name>/android/system/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-
```

### Optimize your Linux installation for future rebuilds:
```
echo "export USE_CCACHE=1" >> ~/.bashrc
prebuilt/linux-x86/ccache/ccache -M 20G
source ~/.bashrc
```
NOTE: 20GB cache here, but can be changed later

### Ready to build!
```
. build/envsetup.sh
brunch cm_dali-eng
```

Subsequent builds only require the brunch command above, but if you modified BoardConfig.mk, you'll need to clean out the build output folder before running brunch (in order to pick up its changes). In that case, run this before using brunch:
```
make clobber
```


### OPTIONAL: If you want to build ClockworkMod:
```
. build/envsetup.sh
. build/tools/device/makerecoveries.sh cm_dali-eng 
```

