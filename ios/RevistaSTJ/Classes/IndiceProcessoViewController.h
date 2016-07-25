//
//  IndiceProcessoViewController.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
@class DetalhaProcessoViewController;
@class IndiceViewController;


@interface IndiceProcessoViewController : UIViewController <UITableViewDataSource, UITableViewDelegate> {
	IBOutlet IndiceViewController *indiceVc;
	IBOutlet DetalhaProcessoViewController *detalheVc;
	NSMutableArray *processos;
	NSMutableArray *lista;
}


//@property (nonatomic,retain) NSString *titulo; 
@property (nonatomic,retain) NSMutableArray *lista;
@property (nonatomic,retain) NSMutableArray *processos;
@property (nonatomic,retain) IBOutlet DetalhaProcessoViewController *detalheVc;
@property (nonatomic,retain) IBOutlet IndiceViewController *indiceVc;


- (UITableViewCell*) CreateMultilinesCell :(NSString*)cellIdentifier;
- (int) heightOfCellWithTitle :(NSString*)titleText;//  andSubtitle:(NSString*)subtitleText;
@end
