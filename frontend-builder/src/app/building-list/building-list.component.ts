import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {Building} from '../building';
import {BuildingService} from './building.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-building-list',
  templateUrl: './building-list.component.html',
  styleUrls: ['./building-list.component.css']
})
export class BuildingListComponent implements OnInit {
  buildings: MatTableDataSource<Building> = new MatTableDataSource();
  displayedColumns = ['id', 'name', 'area', 'cube', 'light', 'heating', 'detail'];
  constructor(
    private buildingService: BuildingService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getBuildings();
  }

  getBuildings(): void {
    this.buildingService.getBuildings().subscribe(resp => {
      this.buildings.data = resp;
    });
  }

  onDetailClick(building: Building): void {
    this.router.navigate(['buildings', building.id]);
  }

}
