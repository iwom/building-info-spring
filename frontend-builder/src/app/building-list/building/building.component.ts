import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Building} from '../../building';

@Component({
  selector: 'app-building',
  templateUrl: './building.component.html',
  styleUrls: ['./building.component.css']
})
export class BuildingComponent implements OnInit {
  building: Building = new Building();
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    console.log(this.route.params['value'].id);
    this.building = this.getBuilding(this.route.params['value'].id);
    console.log(this.building);
  }

  getBuilding(id: number): Building {
    return new Building();
  }
}
