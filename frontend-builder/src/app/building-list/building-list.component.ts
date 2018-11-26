import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {MatIconModule} from '@angular/material';
import {Building} from '../building';
import {Observable} from 'rxjs/Observable';
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
    this.buildings = this.getBuildings();
  }

  getBuildings(): MatTableDataSource<Building> {
    const tableSource = new MatTableDataSource<Building>();
    this.buildingService.getBuildings().subscribe(resp => {
      tableSource.data = resp;
      console.log(tableSource);
    });
    return tableSource;
  }

  onDetailClick(building: Building): void {
    console.log(building);
    this.router.navigate(['buildings', building.id]);
  }

}
