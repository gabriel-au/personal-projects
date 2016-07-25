//
//  UITitle.h
//  EasyBand
//
//  Created by Eduardo Carminati on 13/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface UITitle : UIView {
}
+ (void) setTitle: (NSString *) title onView: (UIView *) view;
+ (CGFloat) getHeight;
@end
