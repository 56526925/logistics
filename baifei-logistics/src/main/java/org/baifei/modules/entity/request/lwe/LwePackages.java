package org.baifei.modules.entity.request.lwe;

import lombok.Data;

@Data
public class LwePackages {
    private String PackageReference;

    private int Length;

    private int Width;

    private int Height;

    private double ActualWeight;
}
