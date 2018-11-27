import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Building} from '../../building';
import {BuildingService} from '../building.service';

@Component({
  selector: 'app-building',
  templateUrl: './building.component.html',
  styleUrls: ['./building.component.css']
})
export class BuildingComponent implements OnInit {
  building: Building = new Building();
  constructor(private route: ActivatedRoute,
              private buildingService: BuildingService,
              private router: Router) { }

  ngOnInit() {
    this.getBuilding(this.route.params['value'].id);
  }

  getBuilding(id: number): void {
    this.buildingService.getBuilding(id).subscribe(resp => {
      this.building = resp;
    });
  }

  goBack() {
    this.router.navigate(['buildings']);
  }

  showFloors() {
    this.router.navigate(['buildings', this.building.id, 'floors']);
  }
}
