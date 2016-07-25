//
//  UILoading.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 20/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface UILoading : UIView {
	UIView *vu;
	UILabel *texto;
}

@property (nonatomic, retain) UILabel *texto;
@property (nonatomic, retain) UIView *vu;
- (id) initWithView: (UIView *) viw;
- (id) initWithView: (UIView *) viw andOrientation: (UIInterfaceOrientation) io;

- (void) stop;
@end
