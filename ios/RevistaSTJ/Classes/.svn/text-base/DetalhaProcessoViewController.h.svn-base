//
//  DetalhaProcessoViewController.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "VOProcesso.h"
#import "VOResultado.h";
@class ConteudoViewController;
@interface DetalhaProcessoViewController : UIViewController <UITableViewDataSource, UITableViewDelegate> {
	VOResultado *resultado;
	
	ConteudoViewController *contVc;
	NSString *tituloProcesso;
	IBOutlet UINavigationItem *navItem;
	
	NSMutableArray *topicos;

}

@property (nonatomic,retain) NSMutableArray *topicos;
@property (nonatomic,retain) VOResultado *resultado;
@property (nonatomic,retain) NSString *tituloProcesso;
@property (nonatomic,retain) ConteudoViewController *contVc;
@property (nonatomic,retain) IBOutlet UINavigationItem *navItem;


@end
