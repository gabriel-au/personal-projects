//
//  PesquisaViewController.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 29/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UILoading.h"

@class ResultadoTableViewController;
@interface PesquisaViewController : UIViewController <UISearchBarDelegate> {
	NSString *parametro;
	IBOutlet ResultadoTableViewController *resultVc;
	
	UIView *lblEfeito;
	
	UILoading *loading;
	

	
}

@property (nonatomic, retain) UILoading *loading;
@property (nonatomic, retain) UIView *lblEfeito;
@property (nonatomic,retain)  IBOutlet ResultadoTableViewController *resultVc;
@property (nonatomic,retain) NSString *parametro;

@end
