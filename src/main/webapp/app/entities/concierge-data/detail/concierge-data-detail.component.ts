import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConciergeData } from '../concierge-data.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
    selector: 'jhi-concierge-data-detail',
    templateUrl: './concierge-data-detail.component.html',
})
export class ConciergeDataDetailComponent implements OnInit {
    conciergeData: IConciergeData | null = null;

    constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(({ conciergeData }) => {
            this.conciergeData = conciergeData;
        });
    }

    byteSize(base64String: string): string {
        return this.dataUtils.byteSize(base64String);
    }

    openFile(base64String: string, contentType: string | null | undefined): void {
        this.dataUtils.openFile(base64String, contentType);
    }

    previousState(): void {
        window.history.back();
    }
}
