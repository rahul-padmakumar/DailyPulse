//
//  AboutListView.swift
//  iosApp
//
//  Created by Rahul Padmakumar on 30/05/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutListView: View {
    
    private struct RowItem: Hashable{
        let title: String
        let subTitle: String
    }
    
    private let items:[RowItem] = {
        let platform = Platform()
        platform.logSystemInfo()
        
        var result: [RowItem] = [
            .init(
                title: "Operating System",
                subTitle: "\(platform.osName) \(platform.osVersion)"
            ),
            .init(
                title: "Device",
                subTitle: platform.deviceModel
            ),
            .init(
                title: "Density",
                subTitle: "@\(platform.deviceDensity)x"
            )
        ]
            
        return result
    }()
    
    var body: some View {
        List{
            ForEach(items, id: \.self){ item in
                VStack(alignment: .leading){
                    Text(item.title)
                        .font(.footnote)
                        .bold()
                    Text(item.subTitle)
                        .font(.body)
                }.padding(.vertical, 4)
            }
        }
    }
}

#Preview {
    AboutListView()
}
