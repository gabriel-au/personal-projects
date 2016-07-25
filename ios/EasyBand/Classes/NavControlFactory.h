//
//  NavControlFactory.h
//  EasyBand
//
//  Created by Eduardo Carminati on 08/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface NavControlFactory : NSObject {

}
+ (UINavigationController *) createNavControl: (UINavigationController *) navCtrl; 
@end
