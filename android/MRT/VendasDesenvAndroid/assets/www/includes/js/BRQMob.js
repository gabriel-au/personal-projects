function getPlataform() {
	var plat = navigator.platform.toLowerCase();
	if (plat.indexOf('linux') != -1) return "android"; else return "desktop";
}

var BRQMobWin = {};

BRQMobWin.exec = function(success, fail, service, action, args) {
	sendNSCommand(service, action, success, fail, JSON.stringify(args));
}

BRQMobWin.addConstructor = function(func) {
	nsConsoleOut.log("estou dentro do BRQMobWin.addConstructor");
	func();
}

BRQMobWin.addPlugin = function(name, obj) {
	if (!window.plugins[name]) window.plugins[name] = obj;
	else nsConsoleErr("Error: Plugin " + name + " already exists.");
}

// *********************************************************************//

var BRQMob = {};

BRQMob.log = function(message) {
	var ambiente = getPlataform();
	if (ambiente == "desktop") {
		nsConsoleOut(message);
	} else if (ambiente == "android") {
		console.log(message);
	} else {
		console.log("ambiente desconhecido: " + ambiente);
	}
}

BRQMob.exec = function(success, fail, service, action, args) {
	var ambiente = getPlataform();
	if (ambiente == "desktop") {
		try {
			BRQMobWin.exec(success, fail, service, action, args);
		} catch (err) {
			nsConsoleOut('[BRQMob.js BRQMob.exec]: ' + err.message);
		}
	} else if (ambiente == "android") {
		try {
			eval ("var successFunc = function(r){" + success + "(r)}");
			eval ("var failFunc = function(r){" + fail + "(r)}");
			PhoneGap.exec(successFunc, failFunc, service, action, args);
		} catch (err) {
			console.log('[BRQMob.js BRQMob.exec]: ' + err.message);
		}
	} else {
		console.log("ambiente desconhecido: " + ambiente);
	}
}

BRQMob.addConstructor = function(func) {
	var ambiente = getPlataform();
	if (ambiente == "desktop") {
		nsConsoleOut("BRQMob -> addConstructor");
		BRQMobWin.addConstructor(func);
	} else if (ambiente == "android") {
		console.log("BRQMob -> addConstructor");
		PhoneGap.addConstructor(func);
	} else {
		console.log("ambiente desconhecido: " + ambiente);
	}
}

BRQMob.addPlugin = function(name, obj) {
	var ambiente = getPlataform();
	if (ambiente == "desktop") {
		nsConsoleOut("BRQMob -> addConstructor");
		BRQMobWin.addPlugin(name, obj);
	} else if (ambiente == "android") {
		console.log("BRQMob -> testando addPlugin");
		PhoneGap.addPlugin(name, obj);
	} else {
		console.log("ambiente desconhecido: " + ambiente);
	}
}
