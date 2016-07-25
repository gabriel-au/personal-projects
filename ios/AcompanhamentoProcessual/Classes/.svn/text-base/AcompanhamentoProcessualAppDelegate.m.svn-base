//
//  AcompanhamentoProcessualAppDelegate.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/12/10.
//  Copyright MobileLifeUtils.com 2010. All rights reserved.
//

#import <QuartzCore/QuartzCore.h>
#import "AcompanhamentoProcessualAppDelegate.h"
//#import "Inicio.h"
#import "VisualizadorWeb.h"
#import "Pushes.h"
#import "Processos.h"

@interface AcompanhamentoProcessualAppDelegate (PrivateCoreDataStack)

@property (nonatomic, retain, readonly) NSManagedObjectModel *managedObjectModel;
@property (nonatomic, retain, readonly) NSManagedObjectContext *managedObjectContext;
@property (nonatomic, retain, readonly) NSPersistentStoreCoordinator *persistentStoreCoordinator;

@end


@implementation AcompanhamentoProcessualAppDelegate

@synthesize sProviderHost,
			ccProvider,
			tbcMain,
			vwDesabilitaAbas,
			vwClienteDesconectado,
			vwProblemaPush,
			window;

#pragma mark -
#pragma mark Application lifecycle

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {    
	
	sProviderHost = [[NSString alloc] initWithString:@"ouropreto.primetecnologia.net"];
	iProviderPort = 7700;
	
	ccProvider = [[CommandController alloc] initWithHost:sProviderHost onPort:iProviderPort];
	[ccProvider setIdAppDelegate:self];
	
	[[UIApplication sharedApplication] registerForRemoteNotificationTypes: UIRemoteNotificationTypeBadge | UIRemoteNotificationTypeSound | UIRemoteNotificationTypeAlert];
	
	tbcMain = [UITabBarController new];
	
	
	VisualizadorWeb *vcVisualizador = [[VisualizadorWeb new] retain];
	vcVisualizador.sEndereco = @"http://m.stj.jus.br/SiteIPhone/";
	vcVisualizador.sTitulo = @"Início";
	[vcVisualizador.view setFrame:CGRectMake(0, 0, 320, 410)];
	[vcVisualizador setBrowserFrame:vcVisualizador.view.frame];
	
	//Inicio *vcInicio = [Inicio new];
	//[vcInicio setCcProvider:ccProvider];
	
	Pushes *vcPushes = [Pushes new];
	[vcPushes setCcProvider:ccProvider];
	
	if ([[launchOptions valueForKey:@"UIApplicationLaunchOptionsRemoteNotificationKey"] valueForKey:@"numeroregistro"] != nil) {
		[vcPushes setSNumeroRegistroPush:[[launchOptions valueForKey:@"UIApplicationLaunchOptionsRemoteNotificationKey"] valueForKey:@"numeroregistro"]];
	}
	
	Processos *vcProcessos = [Processos new];
	[vcProcessos setCcProvider:ccProvider];
	
	vcVisualizador.tabBarItem.image = [UIImage imageNamed:@"inicio.png"];
	vcPushes.tabBarItem.image = [UIImage imageNamed:@"pushes.png"];
	vcProcessos.tabBarItem.image = [UIImage imageNamed:@"acompanhamento.png"];
	
	
	//UINavigationController *ncInicio = [[UINavigationController alloc] initWithRootViewController:vcInicio];
	UINavigationController *ncPushes = [[UINavigationController alloc] initWithRootViewController:vcPushes];
	UINavigationController *ncProcessos = [[UINavigationController alloc] initWithRootViewController:vcProcessos];
	
	tbcMain.viewControllers = [[NSArray alloc] initWithObjects:vcVisualizador, ncPushes, ncProcessos, nil];
	
	ncPushes.navigationBar.tintColor = [UIColor blackColor];
	ncProcessos.navigationBar.tintColor = [UIColor blackColor];
	
	vwDesabilitaAbas = [[UIView alloc] initWithFrame:CGRectMake(0, 430, 320, 50)];
	[vwDesabilitaAbas setBackgroundColor:[[UIColor blackColor] autorelease]];
	[vwDesabilitaAbas setAlpha:0.5];
	
	UILabel *lblAguarde = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 320, 46)];
	[lblAguarde setBackgroundColor:[[UIColor clearColor] autorelease]];
	[lblAguarde setTextColor:[[UIColor whiteColor] autorelease]];
	[lblAguarde setTextAlignment:UITextAlignmentCenter];
	[lblAguarde setText:@"Aguarde..."];
	
	vwClienteDesconectado = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 300, 170)];
	vwClienteDesconectado.layer.cornerRadius = 5;
	[vwClienteDesconectado setBackgroundColor:[[UIColor blackColor] autorelease]];
	[vwClienteDesconectado setAlpha:0.0];
	[vwClienteDesconectado setCenter:CGPointMake(320/2, 480/2)];
	
	UILabel *lblMensagemConexao = [[UILabel alloc] initWithFrame:CGRectMake(10, 10, 280, 100)]; 
	[lblMensagemConexao setBackgroundColor:[[UIColor clearColor] autorelease]];
	[lblMensagemConexao setTextColor:[[UIColor whiteColor] autorelease]];
	[lblMensagemConexao setTextAlignment:UITextAlignmentCenter];
	[lblMensagemConexao setNumberOfLines:0];
	[lblMensagemConexao setText:@"Ocorreu um erro durante a comunicação com o servidor de pushs, por favor verifique a conexão de dados do seu iPhone."];
	
	UIButton *btReconnect = [UIButton buttonWithType:UIButtonTypeRoundedRect];
	[btReconnect setTitle:@"Reconectar ao Servidor" forState:UIControlStateNormal];
	[btReconnect setFrame:CGRectMake(10, 110, 280, 40)];
	[btReconnect addTarget:self 
					action:@selector(reconectarCliente) 
		  forControlEvents:UIControlEventTouchUpInside];
	
	vwProblemaPush = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 300, 170)];
	vwProblemaPush.layer.cornerRadius = 5;
	[vwProblemaPush setBackgroundColor:[[UIColor blackColor] autorelease]];
	[vwProblemaPush setAlpha:0.0];
	[vwProblemaPush setCenter:CGPointMake(320/2, 480/2)];
	
	UILabel *lblMensagemProblemaPush = [[UILabel alloc] initWithFrame:CGRectMake(10, 10, 280, 100)]; 
	[lblMensagemProblemaPush setBackgroundColor:[[UIColor clearColor] autorelease]];
	[lblMensagemProblemaPush setTextColor:[[UIColor whiteColor] autorelease]];
	[lblMensagemProblemaPush setTextAlignment:UITextAlignmentCenter];
	[lblMensagemProblemaPush setNumberOfLines:0];
	[lblMensagemProblemaPush setText:@"Ocorreu um erro durante a geração da identificação do seu dispositivo ou as notificações por push estão desabilitadas."];
	
	UIButton *btRetry = [UIButton buttonWithType:UIButtonTypeRoundedRect];
	[btRetry setTitle:@"Reidentificar Dispositivo" forState:UIControlStateNormal];
	[btRetry setFrame:CGRectMake(10, 110, 280, 40)];
	[btRetry addTarget:self 
				action:@selector(reidentificarCliente) 
	  forControlEvents:UIControlEventTouchUpInside];
	
	[vwProblemaPush addSubview:lblMensagemProblemaPush];
	[vwProblemaPush addSubview:btRetry];
	
	[vwClienteDesconectado addSubview:lblMensagemConexao];
	[vwClienteDesconectado addSubview:btReconnect];
	
	[vwDesabilitaAbas setHidden:YES];
	[vwDesabilitaAbas addSubview:lblAguarde];
	
	[tbcMain.view addSubview:vwDesabilitaAbas];
	[tbcMain.view addSubview:vwClienteDesconectado];
	
	[window addSubview:tbcMain.view];
	[window makeKeyAndVisible];
	[self desabilitaAbas];
	
	UITabBarItem *tbiPushes = [tbcMain.tabBar.items objectAtIndex:1];
	if ([UIApplication sharedApplication].applicationIconBadgeNumber > 0){
		tbiPushes.badgeValue = [NSString stringWithFormat:@"%d", [UIApplication sharedApplication].applicationIconBadgeNumber];
	} else {
		tbiPushes.badgeValue = nil;
	}

	return YES;
}

/*
 Notification
 */

- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
	NSString *sToken = [[deviceToken description] stringByTrimmingCharactersInSet:[NSCharacterSet characterSetWithCharactersInString:@"<>"]]; 
	//NSLog(@"Token recebido: %@", sToken);
	[ccProvider setSDeviceToken:sToken];
	if (![[NSFileManager defaultManager] fileExistsAtPath:[[NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingFormat:@"/%@", @"token.pt"]]) {
		[ccProvider sendCommand:@"cadastrardispositivo"];
		NSError *err;
		[sToken writeToFile:[[NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingFormat:@"/%@", @"token.pt"] 
				 atomically:NO 
				   encoding:NSUTF8StringEncoding
					  error:&err];
	}
	if ([UIApplication sharedApplication].applicationIconBadgeNumber > 0) {
		tbcMain.selectedIndex = 1;
	}
	//[self geracaoTokenOK];
	[self habilitaAbas];
}

- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error {
	//[self geracaoTokenErro];
	//[self desabilitaAbas];
	//token desenv a370dc7d 0608d5ba 28e6b35e 9621e697 9ec6a36a 87b8b4e0 7372483a c9ec1345
	//NSLog(@"%@", [error localizedDescription]);
	[ccProvider setSDeviceToken:@"a370dc7d 0608d5ba 28e6b35e 9621e697 9ec6a36a 87b8b4e0 7372483a c9ec1345"];
}

- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo {
	UITabBarItem *tbiPushes = [tbcMain.tabBar.items objectAtIndex:1];
	tbiPushes.badgeValue = [NSString stringWithFormat:@"%d", [[[userInfo valueForKey:@"aps"] valueForKey:@"badge"] intValue]];
}

/**
 applicationWillTerminate: saves changes in the application's managed object context before the application terminates.
 */
- (void)applicationWillTerminate:(UIApplication *)application {
	
    NSError *error = nil;
    if (managedObjectContext != nil) {
        if ([managedObjectContext hasChanges] && ![managedObjectContext save:&error]) {
			/*
			 Replace this implementation with code to handle the error appropriately.
			 
			 abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development. If it is not possible to recover from the error, display an alert panel that instructs the user to quit the application by pressing the Home button.
			 */
			NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
			abort();
        } 
    }
}

#pragma mark -
#pragma mark Custom

- (void)habilitaAbas {
	[vwDesabilitaAbas setHidden:YES];
}

- (void)desabilitaAbas {
	[vwDesabilitaAbas setHidden:NO];
}

- (void)clienteConectado {
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDuration:0.5];
	[UIView setAnimationCurve:UIViewAnimationCurveEaseInOut];
	[vwClienteDesconectado setAlpha:0.0];
	[UIView commitAnimations];
}

- (void)clienteDesconectado {
	tbcMain.selectedIndex = 0;
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDuration:0.5];
	[UIView setAnimationCurve:UIViewAnimationCurveEaseInOut];
	[vwClienteDesconectado setAlpha:0.8];
	[UIView commitAnimations];
}

- (void)reconectarCliente {
	[ccProvider reconnectToHost:sProviderHost onPort:iProviderPort];
}

- (void)geracaoTokenOK {
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDuration:0.5];
	[UIView setAnimationCurve:UIViewAnimationCurveEaseInOut];
	[vwProblemaPush setAlpha:0.0];
	[UIView commitAnimations];
}

- (void)geracaoTokenErro {
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDuration:0.5];
	[UIView setAnimationCurve:UIViewAnimationCurveEaseInOut];
	[vwProblemaPush setAlpha:0.8];
	[UIView commitAnimations];
}

- (void)reidentificarCliente {
	[[UIApplication sharedApplication] registerForRemoteNotificationTypes: UIRemoteNotificationTypeBadge | UIRemoteNotificationTypeSound | UIRemoteNotificationTypeAlert];
}

#pragma mark -
#pragma mark Core Data stack

/**
 Returns the managed object context for the application.
 If the context doesn't already exist, it is created and bound to the persistent store coordinator for the application.
 */
- (NSManagedObjectContext *) managedObjectContext {
	
    if (managedObjectContext != nil) {
        return managedObjectContext;
    }
	
    NSPersistentStoreCoordinator *coordinator = [self persistentStoreCoordinator];
    if (coordinator != nil) {
        managedObjectContext = [[NSManagedObjectContext alloc] init];
        [managedObjectContext setPersistentStoreCoordinator: coordinator];
    }
    return managedObjectContext;
}


/**
 Returns the managed object model for the application.
 If the model doesn't already exist, it is created by merging all of the models found in the application bundle.
 */
- (NSManagedObjectModel *)managedObjectModel {
	
    if (managedObjectModel != nil) {
        return managedObjectModel;
    }
    managedObjectModel = [[NSManagedObjectModel mergedModelFromBundles:nil] retain];    
    return managedObjectModel;
}


/**
 Returns the persistent store coordinator for the application.
 If the coordinator doesn't already exist, it is created and the application's store added to it.
 */
- (NSPersistentStoreCoordinator *)persistentStoreCoordinator {
	
    if (persistentStoreCoordinator != nil) {
        return persistentStoreCoordinator;
    }
	
    NSURL *storeUrl = [NSURL fileURLWithPath: [[self applicationDocumentsDirectory] stringByAppendingPathComponent: @"AcompanhamentoProcessual.sqlite"]];
	
	NSError *error = nil;
    persistentStoreCoordinator = [[NSPersistentStoreCoordinator alloc] initWithManagedObjectModel:[self managedObjectModel]];
    if (![persistentStoreCoordinator addPersistentStoreWithType:NSSQLiteStoreType configuration:nil URL:storeUrl options:nil error:&error]) {
		/*
		 Replace this implementation with code to handle the error appropriately.
		 
		 abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development. If it is not possible to recover from the error, display an alert panel that instructs the user to quit the application by pressing the Home button.
		 
		 Typical reasons for an error here include:
		 * The persistent store is not accessible
		 * The schema for the persistent store is incompatible with current managed object model
		 Check the error message to determine what the actual problem was.
		 */
		NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
		abort();
    }    
	
    return persistentStoreCoordinator;
}

#pragma mark -
#pragma mark Application's Documents directory

/**
 Returns the path to the application's Documents directory.
 */
- (NSString *)applicationDocumentsDirectory {
	return [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
}


#pragma mark -
#pragma mark Memory management

- (void)dealloc {
	
    [managedObjectContext release];
    [managedObjectModel release];
    [persistentStoreCoordinator release];
	[window release];
	[super dealloc];
}


@end

