# PROJECTS (README SAMPLE) #

# ADD QTY INCREMENT EXTENSION FOR MAGENTO 2 #

Enable quantity increment buttons for products as configured in your advanced inventory by Magento Administrator.

## Specs ##

* Version: 1.0.0
* Extension Key: Neo_AddQtyIncrement

## Features ##

* Enable increment buttons for products
* Increment quantities according to product configuration (by Magento Admin) - Eg. 2 by 2

## Requirements ##
* PHP >= 7

## Compatibility ##
* Magento >= 2

## How to Install ##

**IMPORTANT:** before continuing with these steps, make sure all folder permissions are fixed

**Step 1:** Copy the 'app' directory and paste into root directory of your Magento 2 (if 'app' exists, just copy the 'NeoNetworks' folder to this path '[root]\app\code\')

**Step 2:** To enable 'Neo_AddQtyIncrement' extension, run the following commands:

```
[YOUR_PATH]/php [YOUR_PATH]/bin/magento module:enable --clear-static-content Neo_AddQtyIncrement
[YOUR_PATH]/php [YOUR_PATH]/bin/magento setup:upgrade
```

**Step 3** *(optional):* Run 'setup:di:compile' using command below. It helps to improve your site performance after installation:

```
[YOUR_PATH]/php [YOUR_PATH]/bin/magento setup:di:compile
```

**Step 4:** To finish this installation, flush the cache following this command:

```
[YOUR_PATH]/php [YOUR_PATH]/bin/magento cache:flush
```

**Don't forget to fix permissions issues to finish this installation**

## How to Upgrade ##

**Step 1:** Disable 'Neo_AddQtyIncrement' extension running this command:

```
[YOUR_PATH]/php [YOUR_PATH]/bin/magento module:disable --clear-static-content Neo_AddQtyIncrement
```

**Step 2:** Delete 'NeoNetworks' folder from this path '[root]\app\code\'

**Step 3:** Execute these commands:

```
[YOUR_PATH]/php [YOUR_PATH]/bin/magento setup:upgrade
[YOUR_PATH]/php [YOUR_PATH]/bin/magento cache:flush

```

**Step 4:** Execute installation steps again (above)

## How to Uninstall ##

**Step 1:** Disable 'Neo_AddQtyIncrement' extension running this command:

```
[YOUR_PATH]/php [YOUR_PATH]/bin/magento module:disable --clear-static-content Neo_AddQtyIncrement
```

**Step 2:** Delete 'NeoNetworks' folder from this path '[root]\app\code\'

**Step 3:** Execute these commands:

```
[YOUR_PATH]/php [YOUR_PATH]/bin/magento setup:upgrade
[YOUR_PATH]/php [YOUR_PATH]/bin/magento cache:flush
```

**Don't forget to fix permissions issues to finish this installation**

## Useful tips ##

Sometimes, extensions installations require extra commands to clean cache, static files and similar things. Take a quick look at these commands below.


Removing cache and static files:

```
sudo rm -rf [YOUR_PATH]/var/cache/* [YOUR_PATH]/var/generation [YOUR_PATH]/var/log [YOUR_PATH]/var/page_cache/*
sudo rm -rf [YOUR_PATH]/pub/static
```

Deploying all content files (static and cache):

```
[YOUR_PATH]/php [YOUR_PATH]/bin/magento setup:static-content:deploy
```

Solving permission issues (sample for Bitnami usage):

```
sudo find /opt/bitnami/apps/magento/htdocs/var -type d -exec chmod 775 {} \;
sudo find /opt/bitnami/apps/magento/htdocs/var -type f -exec chmod 664 {} \;
sudo chown -R bitnami:daemon /opt/bitnami/apps/magento/htdocs/var;
sudo find /opt/bitnami/apps/magento/htdocs/pub -type d -exec chmod 775 {} \;
sudo find /opt/bitnami/apps/magento/htdocs/pub -type f -exec chmod 664 {} \;
sudo chown -R bitnami:daemon /opt/bitnami/apps/magento/htdocs/pub;
sudo find /opt/bitnami/apps/magento/htdocs/app -type d -exec chmod 775 {} \;
sudo find /opt/bitnami/apps/magento/htdocs/app -type f -exec chmod 664 {} \;
sudo chown -R bitnami:daemon /opt/bitnami/apps/magento/htdocs/app;
```

Extra commands for permission issues:

```
sudo find /opt/bitnami/apps/magento/htdocs/vendor -type d -exec chmod 775 {} \;
sudo find /opt/bitnami/apps/magento/htdocs/vendor -type f -exec chmod 664 {} \;
sudo chown -R bitnami:daemon /opt/bitnami/apps/magento/htdocs/vendor;
```

### Support ###

* [Neonetworks Support Team](http://www.neonetworks.com.au/#contactsPage)
