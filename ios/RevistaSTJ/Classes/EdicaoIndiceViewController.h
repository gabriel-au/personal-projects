//
//  EdicaoIndice.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 28/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "IndiceViewController.h"

@interface EdicaoIndiceViewController : UIViewController <UIPickerViewDelegate, UIPickerViewDataSource> {
	NSMutableArray *edicoes;
	NSInteger indexSel;
	
	IBOutlet IndiceViewController *indiceVc;
	IBOutlet UIPickerView *combo;
}
@property (nonatomic,retain) IBOutlet UIPickerView *combo;
@property (nonatomic,retain) IBOutlet IndiceViewController *indiceVc;
@property (nonatomic,retain) NSMutableArray *edicoes;
@property (nonatomic) NSInteger indexSel;

- (IBAction) lerEdicao: (id) sender;
- (IBAction) atualizar: (id) sender;
@end
