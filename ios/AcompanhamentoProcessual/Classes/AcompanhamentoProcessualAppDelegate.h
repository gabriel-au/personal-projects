//
//  AcompanhamentoProcessualAppDelegate.h
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/12/10.
//  Copyright MobileLifeUtils.com 2010. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreData/CoreData.h>
#import "CommandController.h"

@interface AcompanhamentoProcessualAppDelegate : NSObject <UIApplicationDelegate> {
	NSManagedObjectModel			*managedObjectModel;
    NSManagedObjectContext			*managedObjectContext;	    
    NSPersistentStoreCoordinator	*persistentStoreCoordinator;
	NSString						*sProviderHost;
	int								iProviderPort;
	CommandController				*ccProvider;
	UITabBarController				*tbcMain;
    UIView							*vwDesabilitaAbas;
	UIView							*vwClienteDesconectado;
	UIView							*vwProblemaPush;
	UIWindow						*window;
}

@property (nonatomic, retain) NSString				*sProviderHost;
@property (nonatomic, retain) CommandController		*ccProvider;
@property (nonatomic, retain) UITabBarController	*tbcMain;
@property (nonatomic, retain) UIView				*vwDesabilitaAbas;
@property (nonatomic, retain) UIView				*vwClienteDesconectado;
@property (nonatomic, retain) UIView				*vwProblemaPush;
@property (nonatomic, retain) IBOutlet UIWindow		*window;

- (void)habilitaAbas;
- (void)desabilitaAbas;
- (void)clienteConectado;
- (void)clienteDesconectado;
- (void)reconectarCliente;
- (void)geracaoTokenOK;
- (void)geracaoTokenErro;
- (void)reidentificarCliente;
- (NSString *)applicationDocumentsDirectory;

@end

