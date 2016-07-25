//
//  Constantes.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 22/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UILoading.h"

@interface Const : NSObject {

	UILoading *loading;
	UIView *logo;
	BOOL photoActiveTab;
	UIInterfaceOrientation orient;
}

@property (nonatomic,retain) UILoading *loading;
@property (nonatomic,retain) UIView *logo;
@property (nonatomic) BOOL photoActiveTab;
@property (nonatomic) UIInterfaceOrientation orient;

+ (Const *) sharedInstance;

@end
